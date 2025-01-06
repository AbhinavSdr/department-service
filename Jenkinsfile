pipeline {	
	agent any	
	tools {	    
		maven 'my-maven'		
		jdk 'jdk-17'	
	}	
	stages {        
		stage('Clone'){			
			steps {git url:'https://github.com/AbhinavSdr/department-service.git', branch:'master'}
		}
		stage('Build'){			
			steps {bat "mvn clean install -DskipTests"}		
		}	
		// stage('Pre-Deploy'){
		// 	steps{bat "docker rm -f department-cntr"
		// 				"docker rmi -f department-img"}
		// }	
		stage('Deploy') {			
			steps { bat "docker build -t department-img ."			    
			            bat "docker run -p 9081:8081 -d --name department-cntr --network my-net department-img"}		
		}		
	}
}