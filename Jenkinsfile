pipeline {
    agent any

    environment {
        MAVEN_HOME = 'C:/Program Files/apache-maven-3.9.9'
        PATH = "${env.PATH}:${env.MAVEN_HOME}/bin"
    }

    stages {
        stage('Checkout') {
            steps {
                // Clonar el repositorio de GitHub
                git branch: 'main', url: 'https://github.com/systemgil/SystemgilFramework.git'
            }
        }

        stage('Build') {
            steps {
                // Compilar el proyecto
                script {
                   try {
    					 sh "\"${MAVEN_HOME}/bin/mvn\" clean install"
					} catch (hudson.AbortException e) {
    				echo "Error en el comando: ${e}"
    				currentBuild.result = 'FAILURE'
}
                   
                }
            }
        }

        stage('Run Tests') {
            steps {
                // Ejecutar las pruebas con Maven y TestNG
                script {
                    // Usar comillas dobles para manejar espacios en la ruta
                    sh '"${env.MAVEN_HOME}\\bin\\"mvn test'
                }
            }
        }

        stage('Generate Reports') {
            steps {
                // Generar reportes de Surefire para TestNG
                script {
                    // Usar comillas dobles para manejar espacios en la ruta
                    sh "\"${env.MAVEN_HOME}/bin/mvn\" surefire-report:report"
                }
            }
        }
    }

    post {
        always {
            // Archivar los reportes generados
            archiveArtifacts artifacts: 'target/surefire-reports/*.xml', allowEmptyArchive: true
            junit 'target/surefire-reports/*.xml'
        }
    }
}
