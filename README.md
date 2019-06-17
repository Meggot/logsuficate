So the general idea here is that you should just be able to add annotations on fields directly at class level
to decide which are to be masked. In order to help debug it sometimes is nice to have consistent log values
for a given object, this is why you can specify in the annotation name a specific string to use for that field. IE:

@Logsuficate("USER-PASSWORD" + email)

Or something.

It uses Logbacks TurboFilter and requires minimal setup.