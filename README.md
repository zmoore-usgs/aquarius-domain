# aquarius-domain

To update this project:
1. Find an AQUARIUS server which answers Publish API calls at http://AQ_HOST/AQUARIUS/Publish/
2. Update pom file version to match the version number displayed at http://AQ_HOST/AQUARIUS/
3. Open `src/main/resources/downloadSwagger.sh` and update the Aquarius Server URL at the top of the file to point to the Server for which you want to generate code.
4. Run `src/main/resources/downloadSwagger.sh` and ensure the files in the `src/main/resources/swagger` directory are updated.
5. Open `src/main/resources/swagger/swagger.json` and change the `basePath` value from the AQUARIUS server to `.`.
6. At this point you should be able to build the project. The project builds sources based on the files currently in `src/main/resources/swagger/` and does NOT automatically run the download script. When you update the swagger files you should commit the changes back to the main repository for this project and tag it with the AQUARIUS version that you ran against so that domain objects for older versions of Aquarius can be re-built at a later date if necessary.