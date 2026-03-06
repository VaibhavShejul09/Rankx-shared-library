def call() {

stage('Package') {

    sh 'mvn package -DskipTests'

    archiveArtifacts artifacts: 'target/*.jar', fingerprint: true

}

}