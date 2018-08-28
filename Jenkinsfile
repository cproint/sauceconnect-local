pipeline {

	tools {	
			maven 'Maven3.5.4'
			jdk 'java8'
	}
	
	stages {
	
		stage ('Initialize and Compile') {
		
			steps { 
					
					sh '''
					echo "Initializing"
					cd /Users/muralitulugu/github_repos/Java-TestNG-Selenium
					mvn initialize
					mvn compile
					echo "Java-TestNG-Selenium Initialization Job completd successfully"
					''' 			
			}
			
	        post {
					success {
							slackSend channel: '#tech_serv_only',
				          	color: 'good',
				          	message: "The pipeline stage Initialize and Compile completed successfully."
					}
			}
		
		}
	
		stage ('Test') {
		
			steps { 
					
					sh '''
					echo "Running Tests"
					cd /Users/muralitulugu/github_repos/Java-TestNG-Selenium
					mvn test
					echo "Java-TestNG-Selenium Running Job completd successfully"
					sh ''' 			
			}
			
			post {
					success {
							slackSend channel: '#tech_serv_only',
				          	color: 'good',
				          	message: "The pipeline stage Test completed successfully."
					}
			}
		
		}
					
		stage ('Clean') {
		
			steps { 
					sh '''
					echo "Cleanup"
					cd /Users/muralitulugu/github_repos/Java-TestNG-Selenium
					mvn clean
					echo "Java-TestNG-Selenium cleanup Job completd successfully"
					sh ''' 			
			}
			
			post {
					success {
							slackSend channel: '#tech_serv_only',
				          	color: 'good',
				          	message: "The pipeline stage clean completed successfully."
					}
			}
		
		}
	
	}

}