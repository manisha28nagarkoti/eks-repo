def call(String region, String ecr_repo){
 sh '''
   aws ecr get-login-password --region $region| docker login --username AWS --password-stdin $ecr_repo

 '''
}
