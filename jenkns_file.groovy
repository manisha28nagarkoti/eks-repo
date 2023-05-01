pipeline{
    success=true
    // git variables
    git_url='git@gitlab.intelligrape.net:bharti-axa/"${param.service-name}"-service.git'    //change
    devops_git_url='git@gitlab.intelligrape.net:bharti-axa/devops.git'      //picking deploy
    
    //docker variables
    docker_registry_url='https://"${param.docker_url}"'   //  ecr url
    docker_registry='${param.docker_url}'             // erc endpoint 
                // erc endpoint 
    docker_repo='"${param.docker_url}"/"${param.service-name}"-service"'    //  service url
     
    env.region="ap-south-1"
    //For Sonarqube use
    env.APPLICATION="${JOB_BASE_NAME}"
    
    wrap([$class: 'BuildUser']) {
    stage('sending notification'){
        currentBuild.displayName = "#" + currentBuild.number + ", Started by ${env.BUILD_USER}" + ", from Branch: ${env.branch_name}"
        env.use=env.BUILD_USER
        sh 'echo $use'
        //notifyStarted()
     }
    }
    stages{

     stage('Clean WorkSpace'){
       sh 'rm -rf *'
     }
     stage('Git CheckOut') { 
      // Get some code from a GitHub repository
       steps{
        Git_CheckOut(git_url)
       }
  }
      stage('Gaining Access for deployment') {
      sh '`aws sts assume-role --role-arn arn:aws:iam::388606509852:role/Shared-Jenkins-Role --role-session-name nitin > op`'
      env.AWS_SECRET_ACCESS_KEY = sh(script:'cat op | grep SecretAccessKey | awk \'{print $2}\' | sed -s \'s/"//g; s/,//g\'', returnStdout: true).trim()
      env.AWS_ACCESS_KEY_ID = sh(script:'cat op | grep AccessKeyId | awk \'{print $2}\' | sed -s \'s/"//g; s/,//g\'', returnStdout: true).trim()
      env.AWS_SESSION_TOKEN = sh(script:'cat op | grep SessionToken | awk \'{print $2}\' | sed -s \'s/"//g; s/,//g\'', returnStdout: true).trim()
  }
stage('Build Docker Image') {
     docker.Build_Docker_Image(region,docker_registry_url,docker_repo)
  }

stage('Push Image to ECR'){
      docker.Push_Image_to_ECR(region,docker_registry_url,docker_repo)
  }
   
stage('Image cleanup'){
     docker.Image_cleanup(region,docker_registry_url,docker_repo)
 
      
  }
   
stage('Helm Update'){
         helm_update(devops_git_url,service-name)
    
     }

    }
}