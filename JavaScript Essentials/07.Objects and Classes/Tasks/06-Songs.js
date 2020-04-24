function solve(input) {

    class Song {

        constructor(typeList, name, time) {

            this.typeList = typeList;

            this.name = name;

            this.time = time;

        }

        getTypeList() {
            return this.typeList;
        }

        printName() {
            console.log(`${this.name}`);
        }

    }

    let allSongs = [];

    let countOfSongs = input.shift();

    for (let index = 0; index < countOfSongs; index++) {

        let [typeList, name, time] = input.shift().split("_");

        let currentSong = new Song(typeList, name, time);

        allSongs.push(currentSong);

    }

    let wantedTypeList = input.shift();

    if (wantedTypeList === "all") {

        for (const song of allSongs) {

            song.printName();

        }

    } else {

        for (const song of allSongs) {

            if (song.getTypeList() === wantedTypeList) {

                song.printName();

            }


        }

    }



}

solve([4,
    'favourite_DownTown_3:14',
    'listenLater_Andalouse_3:24',
    'favourite_In To The Night_3:58',
    'favourite_Live It Up_3:48',
    'listenLater']
);