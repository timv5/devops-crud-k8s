# devops-crud-k8s
Crud springboot application connecting to a mysql database, using Kubernetes and Docker for deployment.
It contains kubernetes deployment and service files for application and database. They are both configured to run in
kubernetes cluster. Project is also using configmap and secret kubernetes files as a good practice.

## Prerequisites & installations
- docker
- minikube
- java 17

## Functionality
1. Endpoint: /test will return static message "test"
2. LOCAL: Endpoint accessible on: http://localhost:8080/books
3. ON KUBERNETES CLUSTER: Endpoint accessible on: http://192.168.105.6:30129/books
- run: minikube ip -> 192.168.105.6
- run: kubectl get services -> port = 30129
- example url of application deployed to kubernetes: http://192.168.105.6:30129

## Steps to run and deploy application in Kubernetes cluster using Minikube
Official documentation:
- https://minikube.sigs.k8s.io/docs/start/
- https://minikube.sigs.k8s.io/docs/drivers/hyperkit/

### Installation of Brew
- install or update brew: brew update

### Installation of Minikube
- install kubectl: brew install kubectl
- install virtual machine (and add networking to it): brew install qemu
  - brew install socket_vmnet
  - brew tap homebrew/services
  - HOMEBREW=$(which brew) && sudo ${HOMEBREW} services start socket_vmnet
- install minikube: brew install minikube
- check if all is okay: minikube version

### Start kubernetes cluster
1. Start kubernetes cluster: https://minikube.sigs.k8s.io/docs/drivers/hyperkit/
- minikube start --driver qemu --network socket_vmnet
- minikube status

2. Give access of docker to minikube
- eval $(minikube docker-env)

3. Apply config map:
- kubectl apply -f mysql-configMap.yaml

4. Apply config map:
- kubectl apply -f mysql-configMap.yaml

5. Apply Kubernetes deployment file - in root folder execute - also contains service definition. Then enable database: 
- kubectl apply -f db-deployment.yaml
- wait for image to be pulled
- write and copy pod name: kubectl get pods
- run: kubectl exec -it {pod_name} /bin/bash
- run (first mysql is domain, user): mysql -h mysql -u root -p 
- enter db password
- run: show databases;
- run (books-db = your database name): use books-db
- output should be: Database changed

4. Go to root folder of application in /devops-crud-k8s and build image
- run: docker build -t devops-crud-k8s:latest .
- check if image is created successfully: docker images

5. Apply Kubernetes app deployment file - in root folder execute - also contains service definition:
- kubectl apply -f app-deployment.yaml

### Some helpful commands

Check logs and statuses:
- kubectl get pods
- kubectl get deployments
- kubectl get services
- kubectl logs {pod_name}
- kubectl cluster-info
- kubectl get configmap
- kubectl get secrets

Run Kubernetes dashboard:
- minikube dashboard

Stop & delete kubernetes cluster - should be run since this is all running locally on computer and 20GB of space if reserved for
this by default.
- minikube stop
- minikube delete

SSH to minikube
- minikube ssh
- check if docker image is applied: docker images

Encrypt db password and username
- run: echo -n 'root' | base64