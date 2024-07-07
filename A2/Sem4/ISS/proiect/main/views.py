import json

from django.contrib.auth import authenticate
from django.contrib import auth
from django.contrib.auth.decorators import user_passes_test
from django.http import JsonResponse
from django.shortcuts import redirect
from django.middleware.csrf import get_token
from django.views.decorators.csrf import ensure_csrf_cookie
from . import models, serializers, utils


# Create your views here.
@ensure_csrf_cookie
def login(request):
    if request.method == 'POST':
        data = json.loads(request.body)
        username = data.get('username')
        password = data.get('password')
        user = None
        try: user = models.OwnUser.objects.get(username=username, password=password)
        except: pass
        if user is not None:
            auth.login(request, user)
            if user.is_superuser:
                return JsonResponse({'role': 'admin'}, status=200)
            elif user.is_sef:
                return JsonResponse({'role': 'sef'}, status=200)
            else:
                try: models.Logare.objects.create(angajat=user)
                except Exception as ex:
                    print(ex)
                    return JsonResponse({'error': "Could not create logare"}, status=500)
                return JsonResponse({'role': 'angajat'}, status=200)
        else:
            return JsonResponse({'error': "Invalid username/password"}, status=401)
    else:
        return JsonResponse({'error': "Wrong HTTP method"}, status=405)


@user_passes_test(lambda u: u.is_authenticated)
def logout(request):
    try:
        if request.user.is_angajat:
            models.Delogare.objects.create(angajat=request.user)
        auth.logout(request)
    except: return JsonResponse({'status': 'error'}, status=500)
    return JsonResponse({'status': 'ok'}, status=200)


def user(request):
    if request.user.is_authenticated:
        json_user = request.user.serialize()
        print(json_user)
        return JsonResponse(json_user, status=200)
    else:
        return JsonResponse({}, status=401)


def get_csrf(request):
    return JsonResponse({'csrfToken': get_token(request)})


@user_passes_test(lambda u: u.is_angajat)
def sarcini(request):
    sarcini = models.Sarcina.objects.filter(angajat=request.user)
    return JsonResponse([sarcina.serialize() for sarcina in sarcini], safe=False, status=200)


@user_passes_test(lambda u: u.is_sef)
def sef_angajati(request):
    print(request.user)
    # get now date at 00:00 time
    begin_of_day, now, _ = utils.getTime()
    angajati_logati = models.Logare.objects.filter(datetime__gte=begin_of_day).order_by('-datetime').all()
    rez = []
    for logare in angajati_logati:
        nr_logari = models.Logare.objects.filter(angajat=logare.angajat, datetime__gte=begin_of_day).count()
        nr_delogari = models.Delogare.objects.filter(angajat=logare.angajat, datetime__gte=begin_of_day).count()
        if nr_logari == nr_delogari + 1 and logare.angajat not in rez:
            rez.append(logare.angajat)

    return JsonResponse([angajat.serialize() for angajat in rez], status=200, safe=False)


@user_passes_test(lambda u: u.is_sef)
def sef_sarcini(request):
    sarcini = models.Sarcina.objects.filter(angajat__sef=request.user)
    return JsonResponse([sarcina.serialize() for sarcina in sarcini], status=200, safe=False)


def sarcina(request, id):
    if request.method == 'PUT':
        try: sarcina=models.Sarcina.objects.get(id=id)
        except: return JsonResponse({'error': "Sarcina nu exista!"})

        if sarcina.completed:
            return JsonResponse({'error': 'Sarcina e deja completata!'})
        sarcina.completed = True
        sarcina.save()

        return JsonResponse({'status': 'ok'}, status=201)

    elif request.method == 'DELETE':
        try: sarcina=models.Sarcina.objects.get(id=id)
        except: return JsonResponse({'error': "Sarcina nu exista!"}, status=400)

        if sarcina.completed:
            return JsonResponse({'error': 'Sarcina e deja completata!'})
        sarcina.delete()

        return JsonResponse({'status': 'ok'}, status=204)

    elif request.method == 'POST':
        data = json.loads(request.body)
        print(data)
        try:
            angajat_id = data.get('angajat')
            cerinta = data.get('cerinta')
            angajat = models.OwnUser.objects.get(id=angajat_id)
            models.Sarcina.objects.create(angajat=angajat, cerinta=cerinta)
        except Exception as ex:
            print(ex)
            return JsonResponse({'error': "Could not create sarcina"}, status=500)
        return JsonResponse({'status': 'ok'}, status=201)

    else:
        return JsonResponse({'error': "Wrong HTTP method"}, status=405)


def angajati(request):
    angajati = models.OwnUser.objects.filter(is_sef=False, is_superuser=False)
    return JsonResponse([angajat.serialize() for angajat in angajati], safe=False, status=200)
