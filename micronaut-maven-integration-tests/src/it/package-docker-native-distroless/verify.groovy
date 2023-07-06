File log = new File(basedir, 'build.log')
assert log.exists()
assert log.text.contains("Generating a mostly static native image")
assert log.text.contains("Using BASE_IMAGE_RUN: gcr.io/distroless/cc-debian10")
assert log.text.contains("-H:+StaticExecutableWithDynamicLibC")