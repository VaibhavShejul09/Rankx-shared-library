def call() {

stage('Unit Test') {

    sh 'mvn test'

}

}