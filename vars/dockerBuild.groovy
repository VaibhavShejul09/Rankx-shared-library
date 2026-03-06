def call(String image) {

    script {

        def tag = "${env.BUILD_NUMBER}-${env.GIT_COMMIT.take(7)}"
        env.IMAGE_TAG = tag

        sh "docker build -t ${image}:${tag} ."

    }
}