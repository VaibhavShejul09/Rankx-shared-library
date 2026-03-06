def call(String serviceName) {
                script {
                  withCredentials([string(credentialsId: 'git-token', variable: 'GIT_TOKEN')]) {
                    sh """
                         # Clean previous helm repo folder
                         # rm -rf rankx-environments

                         
                         # Clone the helm / environment repo
                         git clone https://VaibhavShejul09:$GIT_TOKEN@github.com/VaibhavShejul09/centralized-helm-repo.git

                         #kubectl create namespace my-app
                         helm upgrade --install ${serviceName} centralized-helm-repo/charts/microservice-base -f rankx-environments/$ENVIRONMENT/${serviceName}/values.yaml \
                         --namespace $ENVIRONMENT \
                         --create-namespace \
                         --atomic --wait \
                         --timeout 8m --debug

                   """
               
             }
        }
}
