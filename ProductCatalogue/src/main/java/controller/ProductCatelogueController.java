package controller;

import Utils.ResponseError;
import model.Product;
import service.ProductCatelogueService;

import java.math.BigDecimal;
import java.util.UUID;

import static Utils.JsonUtil.json;
import static Utils.JsonUtil.toJson;
import static spark.Spark.*;


/**
 * Created by janani.sampathkumar on 08/02/2017.
 */
public class ProductCatelogueController {

    public ProductCatelogueController(final ProductCatelogueService productCatelogueService) {

        get("/products", (req, res) -> productCatelogueService.getAllProducts(), json());

        get("/products/:id", (req, res) -> {
            UUID id = UUID.fromString(req.params(":id"));
            Product product = productCatelogueService.getProductById(id);
            if (product != null) {
                return product;
            }
            res.status(400);
            return new ResponseError("No user with id '%s' found", id.toString());
        }, json());

        post("/products", (req, res) -> productCatelogueService.createProduct(
                req.queryParams("name"),
                new BigDecimal(req.queryParams("price")),
                Long.parseLong(req.queryParams("stock"))
        ), json());

        put("/products/:id", (req, res) -> productCatelogueService.updateProduct(
                UUID.fromString(req.params(":id")),
                req.queryParams("name"),
                new BigDecimal(req.queryParams("price")),
                Long.parseLong(req.queryParams("stock"))
        ), json());

        after((req, res) -> {
            res.type("application/json");
        });

        exception(IllegalArgumentException.class, (e, req, res) -> {
            res.status(400);
            res.body(toJson(new ResponseError(e)));
        });
    }

}
