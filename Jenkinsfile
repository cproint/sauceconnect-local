pipeline {
    agent any

    stages {
        stage('Initialize and Compile') {
            steps {
                sh "mvn initialize compile"
                echo "The pipeline stage Initialize and Compile completed successfully."
            }
        }

    }
    
    stages {
        stage('Functional Tests') {
            steps {
                sh "mvn test"
                echo "The pipeline stage Functional Tests completed successfully."
            }
        }

    }
    
    stages {
        stage('Clean') {
            steps {
                sh "mvn clean"
                echo "The pipeline stage Clean completed successfully."
            }
        }

    }
    
}