FROM node:lts-alpine as builder

WORKDIR /var/jenkins_home/workspace/deployProject/front

COPY package*.json ./

RUN npm install

COPY . .

RUN npm run build

FROM nginx:stable-alpine

RUN rm -rf /etc/nginx/conf.d/default.conf
COPY --from=builder /var/jenkins_home/workspace/deployProject/front/nginx/default.conf /etc/nginx/conf.d/default.conf

RUN rm -rf /usr/share/nginx/html/*
COPY --from=builder /var/jenkins_home/workspace/deployProject/front/dist /usr/share/nginx/html

EXPOSE 80
EXPOSE 443
ENTRYPOINT ["nginx", "-g", "daemon off;"]