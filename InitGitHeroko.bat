
git init
heroku git:remote -a dotcom-costaverde-position

cd C:\Dados\Dev\eclipse\workspace-sts\costaverde-position
git add .
git commit -m "Ajustando Horarios"
git push heroku master

heroku logs --tail

