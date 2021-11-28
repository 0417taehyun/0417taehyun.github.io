---
title: "weekwith.me"
---

# weekwith.me

## For Python Engineer

!!! warning

    I don't deploy `weekwith.me` package yet.

    So it won't work to install the package.

    It soon will be ready :rocket:


If you are Python engineer, it is more interesting to follow this ...

<div class="termy">

```console
$ pip install weekwith.me
---> 100%
Successfully installed weekwith.me

$ weekwith.me --help

```

</div>


If you are not, never mind! I will introduce myself here :rocket:  

## Governing Value

{==
I'm a junior engineer who tries hard to distinguish the time between seeing the forest and seeing the trees.
==}

I first started studying programming to spread good social values with the popularization of technology. Specifically I solved the inconvenient and taken for granted in daily life problems with techonology. For example, I solved the inconvenience of wake-up study manullay checked through a thread in the Slack channel through [automatic checking system with Slack API](#we-ake-up), AWS Lambda and CloudWatch Events. Also, when I proceeded project as online, it would be more efficient to check the past the meeting minutes in Slack, where team members used and commnunicated actively, rather than through Notion wiht multiple page depth, 
so I built [Notion Slack integration](#notion-slack-integration) through checking Notion API by network communication because there wasn't the official Notion API at that time.

Additionally, I'm not afraid to use recent technology and very interested in open source. Recently, I built a [web service which provides study concentration analysis with machine learning and report of it](#studeep). When building the service, I should have displayed users' concentration status to others in the same study room in real-time if the client delivers the machine learning data processed with Tensorflow.js, I've successfully applied FastAPI and SocketIO which I'm experiencing for the first time.

Finally, through these various and several experiences, I realized that the populariztion of good technology, which can deliver good socila values, is not completed only with technology. As much as writing good code, communication, documentation and even business knowledge are important. I learned that seeing forest, cooperation and business, is as important as the tree, techonology. So I built [frontend CI/CD](https://www.weekwith.me/ko/devlog/next-js/ci-cd-static-webstie-by-github-actions-and-aws-s3/) by myself with AWS S3, CloudFront and GitHub Actions and managing mailing service, [Soft-Where](https://maily.so/weekwith.me) which have aobut 180 subscribers, to communicate with people who are not programmer for not seeing only the trees but also the forest.


## Channel

* :envelope_with_arrow: **Email** | [0417taehyun@gmail.com](0417taehyun@gmail.com)
* :octocat: **GitHub** | [https://github.com/0417taehyun](https://github.com/0417taehyun)
* :pencil2: **Maily** | [https://maily.so/weekwith.me](https://maily.so/weekwith.me)
* :memo: **Blog** | [https://weekwith.me](https://weekwith.me)


## Education

### Hankuk University of Foreign Studies

#### Bachelor of Science

* **Date**  | 2021. 03. - Present
* **Major** | Computer Science

#### Bachelor of Art

* **Date**  | 2016. 03. - Present
* **Major** | Swedish

### WeCode Bootcamp

#### Backend Engineer

* **Date** | 2020. 07. - 2020. 10.


## Skils

### Tool

* Git, GitHub
* Slack, Kakao Work
* Agit, Notion, Swagger
* GitHub Projects, Asana, Trello

### Backend

* Python, Django, FastAPI
* JavaScript, NodeJS
* MySQL, PostgreSQL
* Django-orm, SQLAlchemy, Sequelize

### DevOps

* Sentry
* GitHub Actions, CircleCI
* AWS (EC2, ELB, RDS, Lambda, S3, CodeDeploy, IAM, Route53, CloudFront, CloudWatch, Certificate Manager)


## Certificate

### Craftsman Information Processing

* **Date** | 2018. 06.
* **Corporation** | Human Resources Development Service of Korea


## Work Experience

### 2020

#### Meat People

> O2O(Online to Offline) service which connects butcher deliveries.

* **Date** | 2020. 12. - 2021. 02.
* **Role** | Django backend engineer freelancer
* **Details** | 
* **Responsibilities**
    * API document managing and MVP service building with Django and drf-yasg
    * Building billing management and automatic notificiation system with integrting Toss Payments and Aligo API 

#### Whatssub

> Subscription management service.

* **Date** | 2020. 09. - 2020. 11.
* **Role** | Python engineer intern
* **Details** | 
* **Responsibilities**
    * Working on the Serverless environment with AWS Lambda
    * Implementing of automatic suscription cancellation service such as a music streaming servcie Melon and a newsletter service Outstanding with Python and Selenium
    * Implementing of Korean phonological unit serach with integrating AWS DynamoDB with Elastcisearch Service and using NGram Tokenizer and unicode

## Project

### 2021

#### FastAPI Boilerplate

> FastAPI bollierplate collection open source.

* **URL** | [https://fastapi.weekwith.me](https://fastapi.weekwith.me)
* **Date** | 2021. 11. - Present
* **Role** | Open source solo project
* **Details** | 
* **Responsibilites**
    * FastAPI boilerplate collection such as RESTful API which built myself
    * Korean-English document with Mkdocs and GitHub Actions

#### ForOur

> Web service which finds suitable flowers through personality

* **URL** | [https://forour.space](https://forour.space)
* **Date** | 2021. 07. - 2021. 09.
* **Role** | FastAPI backend engineer
* **Details** | 
* **Responsibilities**
    * Managing permissions and building a working environment with AWS IAM
    * Building backend CI/CD with GitHub Actions and AWS CodeDeploy
    * Building frontend CI/CD with GitHub Actions and AWS S3, CloudFront

#### Studeep

> Web service which provides study concentration analysis with machine learning and report of it

* **URL** | [https://studeep.com](https://studeep.com)
* **Date** | 2021. 02. - 2021. 08.
* **Role** | FastAPI backend engineer
* **Details** | 
* **Responsibiliites**
    * Implementing of pro
    * Handling Exception as Pydantic validation response with `Custom Exception` for smooth communication with client

#### HUFSpace

> Hakuk Universtiy of Foreign Studies' commnunity web service

* **URL** | [https://github.com/forHUFS](https://github.com/forHUFS)
* **Date** | 2021. 05. - 2021. 11.
* **Role** | NodeJS backend engineer 
* **Responsibilites**
    * Implementing of parsing scholarship information on school web site and cron scheduling with AWS Lambda, CloudWtach Events, Selenium, PyMySQL, and regular expression
    * Implementing of automatic personal information integration and authentication with AWS Lambda, API Gateway, Selenium and PyMySQL
    * Managing API document with swgger-jsdoc and swagger-ui-express

#### We-ake UP

> Slack wake-up automatic checking system

* **URL** | [https://github.com/0417taehyun/we-ake-up](https://github.com/0417taehyun/we-ake-up)
* **Date** | 2021. 02. - 2021. 02.
* **Role** | Solo project
* **Details** | 
* **Responsibilites**
    * Building webhook system with Slack API, GitHub API and AWS Lambda, CloudWatch Events

#### Notion Slack Integration

> Automactic checking the minutes of a meeting written on Notion in Slack

* **URL** | [https://github.com/0417taehyun/notion](https://github.com/0417taehyun/notion)
* **Date** | 2021. 02. - 2021. 02.
* **Role** | Solo project
* **Details** | 
* **Responsibilities**
    * Building webhook system with Slakc API, Notion API and AWS Lambda, API Gateway

### 2020

#### Market Kurly clone project

> Ecommerce service

* **URL** | [https://github.com/0417taehyun/kurly-backend](https://github.com/0417taehyun/kurly-backend)
* **Date** | 2021. 10. - 2021. 10.
* **Role** | Django backend engineer
* **Details** | 
* **Responsibilities**
    * Creating temporary field with django-orm `annotate`, `Value`, `Count` and getting data effeciently
    * Improving performance with caching of lager data using AWS Elasticache
    * Implementing of Korean phonological unit and key-mapping provoked by Korean/English typos serach with integrating AWS DynamoDB with Elastcisearch Service and using NGram Tokenizer and unicode

#### StockX clone project

> Used shoe auction service.

* **URL** | [https://github.com/0417taehyun/westock-backend](https://github.com/0417taehyun/westock-backend)
* **Date** | 2021. 08. - 2021. 09.
* **Role** | Django backend engineer
* **Details** | 
* **Responsibilities**
    * Writing about 400 lines user account API test code with unittest
    * Implementing of Google and Kakao social login with OAuth
    * Implementing of email and password validation with regular expression
    * Implementing of effecient API with comparing field option(`unique`) to django-orm(`filter.exists`) by logging
    * Implementing of user authenticate middleware with Python decorator
    * Deploying and container vitualizing with Docker and Dokcer Hub

#### Hince clone project

> Beauty and cometic products online shopping service.

* **URL** | [https://github.com/0417taehyun/hipce-backend](https://github.com/0417taehyun/hipce-backend)
* **Date** | 2021. 08. - 2021. 08.
* **Role** | Django backend engineer
* **Details** | 
* **Responsibilites**
    * Dynamic web page crawling with Beautiful Soup and Selenium
    * Implementing of a single RESTful API with multiple filters applied using `request` and `Q`
    * Increasing readability and performance with django-orm utilities such as `prefetch related`, `annotate`, `F`, `aggregate`, and `Sum`


## Personl Experience

### Open Source

#### FastAPI Korea Facebook Group

> FastAPI open source contribution and Korean Facebook group management

* **Date** | 2021. 11. - Present

### Interview

> WeCode Bootcamp Interview

<iframe width="1280" height="720" src="https://www.youtube.com/embed/D5Yk64qtVOM" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>

### Study

#### TWIL

> Study which shares a development-log every week, This Week I Learned (TWIL)

* **URL**  | [https://twil.weekwith.me](https://twil.weekwith.me)
* **Date** | 2021. 07. - Present

### Article
