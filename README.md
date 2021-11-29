# CarServices
  To run the project you need to do the following:
  
  1) Download minikube and kubectl, you can find the links here https://kubernetes.io/docs/tasks/tools/
  2) Download docker from this link https://www.docker.com/products/docker-desktop
  3) Download helm from this link https://helm.sh/docs/intro/install/
  4) Open command line then add helm charts with this command: **helm repo add eks https://aws.github.io/eks-charts**
  5) Start minikube with kubernetes version v1.21.2 with the command: **minikube start --kubernetes-version v1.21.2**
  6) Add docker enviroment to minikube to be able to pull local images with these two commands:
      - **minikube docker-env**
      - **@FOR /f "tokens=*" %i IN ('minikube -p minikube docker-env') DO @%i**
  8) Install app mesh custom resources definitions with the command: **kubectl apply -k "https://github.com/aws/eks-charts/stable/appmesh-controller/crds?ref=master"**
  9) Navigate to the root of the car details service and build it using the command: **mvn clean install**
  10) Build docker image: **docker build -t cardetails:1 .**
  11) Navigate to the root of the car reservation service and build it using the command: **mvn clean install**
  12) Build docker image: **docker build -t carreservations:1 .**
  13) Navigate to the root of the car service and build it using the command: **mvn clean install -Dmaven.test.skip=true**
  14) Build docker image: **docker build -t carservice:1 .**
  15) Enable ingress by the command: **minikube addons enable ingress**
  16) Run the image in kubernetes and integrate app mesh with kubernetes by the running following commands:
      - kubectl apply -f namespace.yml
      - kubectl apply -f mesh.yml
      - kubectl apply -f car-details-virtual-node.yml
      - kubectl apply -f car-details-virtual-service.yml
      - kubectl apply -f car-details-deployment.yml
      - kubectl apply -f car-reservation-virtual-node.yml
      - kubectl apply -f car-reservation-virtual-service.yml
      - kubectl apply -f car-reservation-deployment.yml
      - kubectl apply -f configmap.yml
      - kubectl apply -f car-service-virtual-node.yml
      - kubectl apply -f car-service-virtual-service.yml
      - kubectl apply -f car-service-virtual-router.yml
      - kubectl apply -f car-service-deployment.yml
      - kubectl apply -f ingress.yml
  17) Take the IP address of the ingress gateway by running the command: **kubectl get ingress -n car-mircoservices-app --output yaml**
  18) Open **/etc/hosts** as an administrator and add the IP address taken from ingress with **cars.com** as the hostname. 
  19) If ingress didn't work you can do the following:
      - Open another terminal
      - Run the command **minikube service car-service**.
      - Now you can use the second URL that will be shown to connect to the API
  20) Open postman, and test by using these endpoints.
      - To get details of a car send a get request to **/car-details/{ID}** 
      - To reserve a car send a get request to **/reserve/{ID}**
      - To unreserve a car send a get request to **/unreserve/{ID}**
      - To get all unreserved cars send a get request **/available-cars**
