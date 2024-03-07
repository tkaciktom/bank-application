FROM node:16 as build

ARG REACT_APP_PUBLIC_URL
ARG REACT_APP_API_URL

ENV REACT_APP_PUBLIC_URL ${REACT_APP_PUBLIC_URL}
ENV REACT_APP_API_URL ${REACT_APP_API_URL}

WORKDIR /app

COPY package.json package-lock.json ./

RUN npm install

COPY . ./

RUN npm run build

FROM nginx:alpine

RUN rm /etc/nginx/conf.d/default.conf

RUN echo $'server {\n\
    listen 80;\n\
    \n\
    location = / {\n\
        return 301 $scheme://$http_host$uri/bankapp/;\n\
    }\n\
    \n\
    location /bankapp/ {\n\
        alias /usr/share/nginx/html/bankapp/;\n\
        try_files $uri $uri/ /bankapp/index.html;\n\
    }\n\
    \n\
    location /static/ {\n\
        alias /usr/share/nginx/html/bankapp/static/;\n\
    }\n\
    \n\
    location ~ ^/(asset-manifest.json|logo512.png|robots.txt|favicon.ico|logo192.png|manifest.json)$ {\n\
        alias /usr/share/nginx/html/bankapp/$1;\n\
    }\n\
}' > /etc/nginx/conf.d/default.conf

COPY --from=build /app/build /usr/share/nginx/html/bankapp

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]

