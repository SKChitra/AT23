Feature: Petstore Everything About Your Pets API

  Scenario: Post -  upload image and petid and validate response status code
 	  Given user uploads image, petid		
		|image                                   |petid|
		|./src/test/resources/Images/Sugar.jpeg  |100  |
		|./src/test/resources/Images/Oreo.jpeg   |101  |
		|./src/test/resources/Images/Hershey.jpeg|102  |
	
		Then user updates petstatus
		Then user adds new pet to the store with petid, name and status
		When user filters pets by status
		|status|
		|pending|
		|available|
		|sold|
		
		Then user enters petid to delete and validates the response code
		Then user finds pet by id