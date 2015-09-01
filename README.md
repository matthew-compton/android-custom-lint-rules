Custom Lint Rules
============

This branch has a custom lint rule for identifying enums and insulting the programmers that use them.

##### Build the validator

`./gradlew build`

##### Copy to the lint directory

`cp ./build/libs/android-custom-lint-rules.jar ~/.android/lint/`

##### Verify whether the issues are registered with lint

`lint --show EnumDetector`

##### Run lint

`./gradlew lint`

> Note: If you can't run `lint` directly, you may want to include android tools `PATH` in your
 `~/.bash_profile`.
> (i.e. `PATH=$PATH:~/Library/Android/sdk/tools`)
>
> Then run `source ~/.bash_profile`.

License
-------
Licensed under the Apache 2.0 license. See the LICENSE file for details.