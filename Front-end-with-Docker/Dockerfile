FROM node:alpine as build-step
RUN mkdir -p /app
WORKDIR /app
COPY package.json /app
#importante che tutte le dipendenze del sistema siano specificate nel package.json e non ve ne siano installate manualmente
#altrimenti nella fase di dockeraggio non verrano ritrovate le rispettive dipendenze
RUN npm install --force
RUN npm install -g @angular/cli@latest

#importante mettere --force per i conflitti delle dipendenze
COPY . /app
RUN npm run build

# Stage 2
FROM nginx:1.17.1-alpine
COPY --from=build-step /app/dist/advanced-con-front-end /usr/share/nginx/html
#seguire poi le istruzioni a https://dzone.com/articles/how-to-dockerize-angular-app


#Affinchè possa completarsi la buil non (importante questo non) deve essere presente la cartella nodemodules nell'app angular originale



