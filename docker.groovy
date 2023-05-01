Build_Docker_Image(region,docker_registry_url,docker_repo){
    sh '`aws ecr get-login --region region --no-include-email`'
      docker.withRegistry(${docker_registry_url}) {
        docker.build(${docker_repo})
      }
}

Push_Image_to_ECR(region,docker_registry_url,docker_repo){
    sh '`aws ecr get-login --region $region --no-include-email`'
     docker.withRegistry(${docker_registry_url}) {
        docker.image(${docker_repo}).push("${tag}")
      }
}



Image_cleanup(docker_registry_url,docker_repo)
         docker.withRegistry(${docker_registry_url}) {
          env.imagename = docker_repo
          sh 'echo removing $imagename and $imagename:$tag'
          sh 'docker rmi $imagename $imagename:$tag'
      }