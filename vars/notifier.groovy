def call(){
  wrap([$class: 'BuildUser']) {
    stage('sending notification'){
        currentBuild.displayName = "#" + currentBuild.number + ", Started by ${env.BUILD_USER}" + ", from Branch: ${env.branch_name}"
        env.use=env.BUILD_USER
        sh 'echo $use'
        //notifyStarted()
     }
    }
    
     }
