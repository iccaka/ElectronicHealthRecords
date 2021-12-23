# vue.js medical app

> This program is growing as a part of the thesis for the postgraduate program in
> University of Piraeus.
>
> This project is referring to a medical application in which every user is able to
> add their AMKA and the AMKA of their parents and check based on their
> heredity  the percentage to get an disease of their relatives.

### Application mode  

* Register to the application with all the mandatory information 

  Login with email and password  

* Changing password

* Adding the AMKA of relatives  

* User can see profile info and check the percentage to get one of the diseases

* Choosing diseases the user might have  

* Showing all the diseases user has added

* Medical articles(under construction)

* Medical articles regarding the diseases that used has added to their profile(under
  construction) 


Requirement for the application to run is [backend]( https://github.com/vaggeliskits/ruby_medical_app) and you can find more information about that in the project.

### Install [Vue.js](https://vuejs.org/v2/guide/installation.html)

### Setup your APP 

Path to index.js

```javascript
config/index.js
```

Change your local ip in index.js file

```javascript
 // proxy all requests starting with /api to rails
      '/api/': {
        target: 'http://192.168.1.*:3000',
        changeOrigin: true,
        pathRewrite: {
          '^/api': '/api/v1/'
        }
      }
```

 and 

```javascript
 // Various Dev Server settings
    host: '192.168.1.*', // can be overwritten by process.env.HOST
    port: 8080, // can be overwritten by process.env.PORT, if port is in use, a free one will be determined
```

## Build Setup

```bash
# install dependencies
npm install

# serve with hot reload at localhost:8080
npm run dev

# build for production with minification
npm run build

# build for production and view the bundle analyzer report
npm run build --report
```



For a detailed explanation on how things work, check out the [guide](http://vuejs-templates.github.io/webpack/) and [docs for vue-loader](http://vuejs.github.io/vue-loader).