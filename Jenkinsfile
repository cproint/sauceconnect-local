node {
    stage('Checkout') {
        checkout scm
    }

    stage('Initialize and Compile') {
        sh 'mvn initialize compile'
        echo "The pipeline stage Initialize and Compile completed successfully."
    }
    
    stage('Test') {
        sh 'mvn test'
        echo "The pipeline stage Test completed successfully."
    }
    
   stage('Clean') {
        sh 'mvn clean'
        echo "The pipeline stage Clean completed successfully."
    }
    
}    