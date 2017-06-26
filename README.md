# aquarius-domain

To update this project:
1. Find an AQUARIUS server which answers Publish API calls at http://AQ_HOST/AQUARIUS/Publish/
2. Update pom file version to match the version number displayed at http://AQ_HOST/AQUARIUS/
3. Run `src/main/resources/buildIt.sh AQ_HOST` to pull down and manipulate the XSDs. The raw XSDs will download into src/main/resources/rawAqSchemas. These files should remain in their original form for record. They will be copied into src/main/resources/alteredAqSchemas for any manipulations needed by manipulateDataStructures.sh. Update manipulateDataStructures.sh and rerun `src/main/resources/buildIt.sh AQ_HOST` as necessary. You may also run `src/main/resources/manipulateDataStructures.sh` in isolation if you are manually placing the original XSDs into src/main/resources/rawAqSchemas. All commands should be run from the root of the project.
4. Perform any final manual XSD manipulations needed to XSD files in src/main/resources/alteredAqSchemas.
5. Check-in final XSDs.