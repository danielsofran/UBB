# Generated by Django 4.1.7 on 2023-04-05 14:43

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('main', '0001_initial'),
    ]

    operations = [
        migrations.AlterModelOptions(
            name='ownuser',
            options={'verbose_name': 'Cont', 'verbose_name_plural': 'Conturi'},
        ),
        migrations.AlterModelOptions(
            name='sarcina',
            options={'verbose_name': 'Sarcina', 'verbose_name_plural': 'Sarcini'},
        ),
        migrations.AddField(
            model_name='ownuser',
            name='last_logout',
            field=models.DateTimeField(blank=True, null=True),
        ),
    ]
