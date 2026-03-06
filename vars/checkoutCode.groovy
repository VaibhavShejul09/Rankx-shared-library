def call() {

stage('Checkout the code') {

    cleanWs()
    checkout scm

}

}