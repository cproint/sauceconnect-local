node {
    stage('Checkout') {
        checkout scm
    }

    stage('Initialize and Compile') {
        sh 'mvn initialize compile'
    }
    
    post {
			success {
	          	message: "The pipeline stage Initialize and Compile completed successfully."
			}

}    