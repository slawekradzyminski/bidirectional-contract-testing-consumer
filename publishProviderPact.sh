 pactflow publish-provider-contract ./openapi.json \
  --broker-token=TOKEN \
  --broker-base-url=https://awesome-testing.pactflow.io \
  --content-type application/json \
  --provider the-provider \
  --provider-app-version 0.0.4-SNAPSHOT \
  --verification-results-content-type text/plain \
  --verification-results ./result.txt \
  --verification-success \
  --verifier self-verification \
  --verbose