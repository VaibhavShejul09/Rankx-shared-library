def call() {

    sh 'mvn package -DskipTests'

    archiveArtifacts artifacts: 'target/*.jar', fingerprint: true

}