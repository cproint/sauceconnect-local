pipeline {
    agent any

    stages {
        stage('Initialize and Compile') {
            steps {          	
            	withMaven(maven : 'maven_3.5.4') {
            	                                     sh "mvn initialize compile"
            	                      
            	                  }

                echo "The pipeline stage Initialize and Compile completed successfully."
            }
        }

        stage('Functional Tests') {
            steps {
               withMaven(maven : 'maven_3.5.4')  {
         
                                   sh "mvn test"    
         
                                 }
          
              
                echo "The pipeline stage Functional Tests completed successfully."
            }
        }

        stage('Clean') {
            steps {
                withMaven(maven : 'maven_3.5.4') {
                                         sh "mvn clean"        
                                  }

     
                echo "The pipeline stage Clean completed successfully."
            }
        }

    }
    
}