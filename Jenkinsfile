node {
    stage('Checkout') {
        checkout scm
    }

    stage('Initialize and Compile') {
        sh 'mvn initialize compile'
        echo "The pipeline stage Initialize and Compile completed successfully."
    }
    
}    