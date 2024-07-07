from django.contrib.auth.models import AbstractUser
from django.core.exceptions import ValidationError
from django.db import models


class OwnUser(AbstractUser):
    is_sef = models.BooleanField(default=False)
    sef = models.ForeignKey('self', on_delete=models.SET_NULL, null=True, blank=True)

    class Meta:
        verbose_name = 'Cont'
        verbose_name_plural = 'Conturi'

    def __str__(self):
        role = 'Admin' if self.is_superuser else 'Sef' if self.is_sef else 'Angajat'
        return f"{self.name}, {role}"

    @property
    def name(self):
        return f"{self.last_name} {self.first_name}"

    @property
    def is_angajat(self):
        return not self.is_superuser and not self.is_sef

    @property
    def is_admin(self):
        return self.is_superuser

    def clean(self):
        errors = {}
        if self.is_angajat:
            if self.sef is None:
                errors['sef'] = 'Angajatul trebuie sa aiba un sef'
            elif self.sef.is_sef is False:
                errors['sef'] = 'Seful unui angajat trebuie sa aiba rolul de sef'
        if (self.is_sef or self.is_admin) and self.sef is not None:
            errors['sef'] = 'Sefii si adminii nu au sef'
        if errors:
            raise ValidationError(errors)

    def serialize(self):
        return {
            'id': self.id,
            'username': self.username,
            'email': self.email,
            'first_name': self.first_name,
            'last_name': self.last_name,
            'is_superuser': self.is_superuser,
            'is_sef': self.is_sef,
        }


class Sarcina(models.Model):
    angajat = models.ForeignKey(OwnUser, on_delete=models.CASCADE)
    cerinta = models.TextField(blank=False)
    datetime = models.DateTimeField(auto_now_add=True)
    completed = models.BooleanField(default=False)

    class Meta:
        verbose_name = 'Sarcina'
        verbose_name_plural = 'Sarcini'

    def __str__(self):
        return f"{self.angajat.name}, {self.cerinta[:20] + '...' if len(self.cerinta) > 20 else self.cerinta}, {self.datetime}, {'Completata' if self.completed else 'In lucru'}"

    def clean(self):
        errors = {}
        if self.angajat.is_angajat is False:
            errors['angajat'] = 'Sarcina trebuie sa fie asignata unui angajat'
        if errors:
            raise ValidationError(errors)

    def serialize(self):
        return {
            'id': self.id,
            'angajat': self.angajat.serialize(),
            'cerinta': self.cerinta,
            'datetime': self.datetime,
            'completed': self.completed,
        }


class Logare(models.Model):
    angajat = models.ForeignKey(OwnUser, on_delete=models.CASCADE)
    datetime = models.DateTimeField(auto_now_add=True)

    class Meta:
        verbose_name = 'Logare'
        verbose_name_plural = 'Logari'

    def __str__(self):
        return f"{self.angajat.name}, {self.datetime}"

    def serialize(self):
        return {
            'id': self.id,
            'angajat': self.angajat.serialize(),
            'datetime': self.datetime,
        }


class Delogare(models.Model):
    angajat = models.ForeignKey(OwnUser, on_delete=models.CASCADE)
    datetime = models.DateTimeField(auto_now_add=True)

    class Meta:
        verbose_name = 'Delogare'
        verbose_name_plural = 'Delogari'

    def __str__(self):
        return f"{self.angajat.name}, {self.datetime}"

    def serialize(self):
        return {
            'id': self.id,
            'angajat': self.angajat.serialize(),
            'datetime': self.datetime,
        }

