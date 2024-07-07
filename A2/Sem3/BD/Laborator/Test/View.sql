SELECT A.nume+': '+R.textr as Replici FROM ActoriReplici EA
	INNER JOIN Actori A ON EA.actor_id=A.id
	INNER JOIN Replici R ON EA.replica_id=R.id
	INNER JOIN Episoade E ON R.episod_id=E.id
	ORDER BY E.ord_cron, R.moment

