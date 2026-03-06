def call(Map config) {

pipeline {

agent any

environment {
    DOCKER_IMAGE = config.dockerImage
}

stages {

stage('Setup Environment') {
    steps {
        setEnvironment()
    }
}

stage('Checkout') {
    steps {
        checkoutCode()
    }
}

stage('Compile') {
    steps {
        compileJava()
    }
}

stage('Unit Test') {
    steps {
        unitTest()
    }
}

stage('Package') {
    steps {
        packageJar()
    }
}

stage('Docker Build') {
    steps {
        dockerBuild(DOCKER_IMAGE)
    }
}

stage('Docker Push') {
    steps {
        dockerPush(DOCKER_IMAGE)
    }
}

stage('Update Helm Values') {
    steps {
        updateHelmValues(config.serviceName)
    }
}

stage('Manual Approval for Prod') {
    when {
        branch 'master'
    }

    steps {
        timeout(time: 10, unit: 'MINUTES') {
            input message: "Approve Prod Deployment?"
        }
    }
}

stage('Deploy') {
    steps {
        helmDeploy(config.serviceName)
    }
}

}

}

}