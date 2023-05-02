def call(String ecr_repo,String repo_name,String tag,String image_name){
sh '''
docker tag $image_name:$tag $ecr_repo/$repo_name:$tag
docker push $ecr_repo/$repo_name:$tag

'''


}
