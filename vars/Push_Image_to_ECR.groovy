def call(String ecr_repo,String image_name){
sh '''
docker tag $image_name:$tag $ecr_repo/$repo_name:$tag
docker push $ecr_repo/$image_name:$tag

'''


}
