{{/*
Common labels
*/}}
{{- define "uvaliavaty-app.labels" }}
  labels:
    date: {{ now | htmlDate }}
    version: {{ .Chart.Version }}
{{- end }}
