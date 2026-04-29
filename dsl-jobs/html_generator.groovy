pipelineJob('html-generator') {

    description('Pipeline job that builds, tests, and generates dynamic HTML')

    parameters {
        stringParam(
            'DEPLOYED_BY',
            '',
            'Identifies who generated the latest HTML.'
        )
    }

    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url('https://github.com/sivarama-bheesetti/go-html-render.git')
                        credentials('go-html-renderer')   // Jenkins credential ID
                    }
                    branches('dynamic-html')
                }
            }
            scriptPath('Jenkinsfile')   // Path inside the repo
        }
    }
}
