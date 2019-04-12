A brief explanation of what's happening in Pact:

Consumer side
1. Write test for the specific request url, in terms of REST
2. Actual request has been run against the "Pact Mock Provider"
3. Based on the defined tests, it generates the contract - "minimal expected response"
4. Pact contract file now has expected request and minimal expected response
5. It is then shared to the Provider manually or uploaded through Pact Broker or some other means

Provider side:
1. Set config on the provider to match with any specific consumer Pact
2. "Pact Mock Service" now makes the call to the Provider - for the expected request
3. Provider gives the actual response
4. Actual response is then been compared with the "minimal expected response" in the Pact contract file