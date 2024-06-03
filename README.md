# Lark Client

Dead simple client for Lark Bot API.

Provide a simple way to interact with Lark Bot webhook, with friendlier (but incompetent, of course) api.

For more complex use case, consider using [official java-sdk](https://github.com/larksuite/oapi-sdk-java).

## Installation

Add library to gradle dependencies

```groovy
dependencies {
    implementation 'io.github.mawngo:lark-client:1.0.0'
}
```

## Usage

Example usage:

```java
public class Main {
    public static void main(String[] args) {
        final var bot = NotificationBot.newSecuredBot("https://open.larksuite.com/open-apis/bot/v2/hook/<id>", "<key>");
        // Example send rich text, using sendUnchecked to throw RuntimeException.
        bot.sendUnchecked(
            Message.richText()
                .title("ok")
                .textLine("test")

                .line()
                .text("this must be ")
                .link("https://google.com", "on the same line")
                .end()

                .textLine("this", "must", "separated.", " this must not")
        );
    }
}
```

More example can be found in [examples](src/main/java/io/github/mawngo/lark/examples)

### Supported Message Type

- text
- image
- share chat
- rich text (support multilingual)
- template card
- ~~card~~ not supported, but can send card request using `Message#raw`

## Development

To build project

```shell
./gradlew build
```
