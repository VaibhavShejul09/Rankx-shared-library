def call(){
    stage('Set Environment Based on Branch') {

     script {

        def branchMap = [
            develop: "dev",
            release: "qa",
            master : "prod"
        ]

        if (!branchMap.containsKey(env.BRANCH_NAME)) {
            error "Branch ${env.BRANCH_NAME} not allowed"
        }

        env.ENVIRONMENT = branchMap[env.BRANCH_NAME]

        echo "Branch: ${env.BRANCH_NAME}"
        echo "Deploying to namespace: ${env.ENVIRONMENT}"
    }

}   
}
