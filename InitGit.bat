cd C:\Dados\Dev\eclipse\workspace-sts\dotcom-aurora
echo "# Dotcom Aurora" >> README.md

rem --- Primeiro Commit
git init
git add .
git commit -m "primeiro commit"
git remote add origin https://github.com/Daniel-Pimenta/dotcom-aurora.git
git push -u origin master

rem --- Commits Adicionais
git add .
git commit -m "Versão 2 com controle de data"
git push -u origin master







