def call() {

stage('Clean & Compile') {

    sh 'mvn clean compile'

}

}