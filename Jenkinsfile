pipeline {
    agent any
    
    tools {
        maven 'maven_3.5.4'
    }


    stages {
        stage('Initialize and Compile') {
            steps {          	
				sh "mvn initialize compile"
                echo "The pipeline stage Initialize and Compile completed successfully."
            }
        }

        stage('Functional Tests') {
            steps {
 				sh "mvn test"    
                echo "The pipeline stage Functional Tests completed successfully."
            }
        }

        stage('Clean') {
            steps {
				sh "mvn clean"        
                echo "The pipeline stage Clean completed successfully."
            }
        }

    }
    
}