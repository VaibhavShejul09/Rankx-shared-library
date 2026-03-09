def call(Map config) {

pipeline {

agent any


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
        dockerBuild(config.dockerImage)
    }
}

stage('Docker Push') {
    steps {
        dockerPush(config.dockerImage)
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
/*
stage('Deploy') {
    steps {
        helmDeploy(config.serviceName)
    }
}
*/
}

}

}
