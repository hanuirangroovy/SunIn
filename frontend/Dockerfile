# # ##build stage
# FROM node:lts-alpine as build-stage
# WORKDIR /app
# COPY package*.json ./
# RUN npm install --production
# COPY . .
# RUN npm run build

# ### production stage
# FROM nginx:stable-alpine as production-stage
# COPY --from=build-stage /app/dist /usr/share/nginx/html
# EXPOSE 80
# CMD ["nginx", "-g", "daemon off;"]

###########

# FROM node:alpine

# ENV PORT 3000

# WORKDIR /usr/src/app

# COPY package*.json ./
# RUN npm install

# COPY ./ ./

# ENV NODE_ENV production

# RUN npm run build

# CMD ["npm", "run", "start"]

# FROM node
# WORKDIR /usr/src/app
# COPY package*.json ./
# RUN npm install --production
# COPY . .
# RUN npm run build

FROM node:alpine

ENV PORT 3000

WORKDIR /usr/src/app

COPY package*.json ./
RUN npm install

COPY ./ ./

ENV NODE_ENV production

RUN npm run build

CMD ["npm", "run", "start"]