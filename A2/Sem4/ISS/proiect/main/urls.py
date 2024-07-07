from django.contrib import admin
from django.urls import path, include
from main import views

urlpatterns = [
    path('csrf/', views.get_csrf, name='csrf'),
    path('', views.login, name='login'),
    path('user/', views.user, name='user'),
    path('login/', views.login, name='login'),
    path('logout/', views.logout, name='logout'),

    path('home_angajat/', views.sarcini, name='home_angajat'),
    path('sarcina/<int:id>/', views.sarcina, name='solve_sarcina'),
    path('sarcina/add/', views.sarcina, name='add_sarcina'),
    path('sef_angajati/', views.sef_angajati, name='sef_angajati'),
    path('sef_sarcini/', views.sef_sarcini, name='sef_sarcini'),
    path('angajati/', views.angajati, name='angajati'),

]
