// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
//15 lines output
//5 lines input

    single_char_strings, two_char_strings = analyze_text(text)
            my_alphas = list(" " + ascii_lowercase)
            // what is our total sum of probabilities.
            //first case when we have space at start.
            two_char_strings[" " + text[0]] += 1
            def select_test(test_name="TEST"):
            """
    Select proper test mode.

    :param test_name: test name
    :type test_name: str
    :return: None
    """
            error_flag_2 = 0
            error_flag_1 = doctest.testfile(
            "test.py",
            optionflags=doctest.NORMALIZE_WHITESPACE | doctest.ELLIPSIS
            | doctest.IGNORE_EXCEPTION_DETAIL,
            verbose=False)[0]
            if test_name == "TEST2":
            error_flag_2 = doctest.testfile(
            "test2.py",
            optionflags=doctest.NORMALIZE_WHITESPACE | doctest.ELLIPSIS | doctest.IGNORE_EXCEPTION_DETAIL,
            verbose=False)[0]
            error_flag = error_flag_1 + error_flag_2
            if error_flag == 0:
            print("\n" + test_name + " Passed")
            sys.exit(error_flag)
            else:
            print("\n" + test_name + " Failed")
            sys.exit(error_flag)


            def main():
            """
    CLI main function.

    :return: None
    """
            args = sys.argv
            if len(args) > 1:
            if args[1].upper() == "TEST":
            select_test("TEST")
            elif args[1].upper() == "TEST2":
            select_test("TEST2")
            elif args[1].upper() in ["LIST", "ARTS"]:
            art_list()
            elif args[1].upper() == "FONTS":
            font_list()
            elif len(args) > 2:
            if args[1].upper() == "ALL":
            if "ARTFonts" not in os.listdir(os.getcwd()):
            os.mkdir("ARTFonts")
            zipf = zipfile.ZipFile(
            os.path.join(
            "ARTFonts",
            "ALL_FONT" + '.zip'),
            'w',
            zipfile.ZIP_DEFLATED)
            print("Generating . . . ")
            for font in FONT_MAP.keys():
            tsave(
            args[2],
            filename=os.path.join(
            "ARTFonts",
            font + ".txt"),
            print_status=False,
            font=font)
            zipf.write(
            os.path.join(
            "ARTFonts",
            font + ".txt"),
            font + ".txt")
            zipf.close()
            print("Done!")
            print("File -- > " +
            str(os.path.join("ARTFonts", "ALL_FONT" + '.zip')))
            elif args[1].upper() == "TEXT":
            if len(args) > 3:
            try:
            tprint(args[2], font=args[3])
            except artError as e:
            print(str(e))
            except UnicodeEncodeError:
            print(FONT_ENVIRONMENT_WARNING)
            else:
            tprint(args[2])
            elif args[1].upper() == "SAVE":
            if len(args) > 3:
            tsave(args[2], font=args[3])
            else:
            tsave(args[2])
            elif args[1].upper() in ["SHAPE", "ART"]:
            try:
            aprint(args[2])
            except artError as e:
            print(str(e))
            except UnicodeEncodeError:
            print(ART_ENVIRONMENT_WARNING)
            else:
            help_func()
            else:
            help_func()
            else:
            help_func()


            if __name__ == "__main__":
            main()
    def font_size_splitter(font_map):
        """
    Split fonts to 4 category (small,medium,large,xlarge) by maximum length of letter in each font.

    :param font_map: input fontmap
    :type font_map : dict
    :return: splitted fonts as dict
    """
        small_font = []
        medium_font = []
        large_font = []
        xlarge_font = []
        fonts = set(font_map.keys()) - set(RANDOM_FILTERED_FONTS)
        for font in fonts:
        length = max(map(len, font_map[font][0].values()))
        if length <= FONT_SMALL_THRESHOLD:
        small_font.append(font)
        elif length > FONT_SMALL_THRESHOLD and length <= FONT_MEDIUM_THRESHOLD:
        medium_font.append(font)
        elif length > FONT_MEDIUM_THRESHOLD and length <= FONT_LARGE_THRESHOLD:
        large_font.append(font)
        else:
        xlarge_font.append(font)
        return {
        "small_list": small_font,
        "medium_list": medium_font,
        "large_list": large_font,
        "xlarge_list": xlarge_font}


        RND_SIZE_DICT = font_size_splitter(FONT_MAP)  # pragma: no cover


        def line(char="*", number=30):
        """
    Print line of chars.

    :param char: input character
    :type char:str
    :param number: number of characters
    :return: None
    """
        print(char * number)


        def font_list(text="test", mode="all"):
        """
    Print all fonts.

    :param text : input text
    :type text : str
    :param mode: fonts mode (all,ascii,non-ascii)
    :type mode: str
    :return: None
    """
        fonts = set(FONT_NAMES)
        if mode.lower() == "ascii":
        fonts = fonts - set(NON_ASCII_FONTS)
        if mode.lower() == "non-ascii":
        fonts = set(NON_ASCII_FONTS)
        for item in sorted(list(fonts)):
        print(str(item) + " : ")
        text_temp = text + "\n"
        tprint(text_temp, str(item))


        def art_list(mode="all"):
        """
    Print all 1-Line arts.

    :param mode: fonts mode (all,ascii,non-ascii)
    :type mode: str
    :return: None
    """
        arts = set(ART_NAMES)
        if mode.lower() == "ascii":
        arts = arts - set(NON_ASCII_ARTS)
        if mode.lower() == "non-ascii":
        arts = set(NON_ASCII_ARTS)
        for i in sorted(list(arts)):
        print(i)
        aprint(i)
        line()


        def decor_list(text="test", font="fancy6"):
        """
    Print all decorations.

    :param text : input text
    :type text : str
    :param font: input font
    :type font:str
    :return: None
    """
        for decor in DECORATION_NAMES:
        print(decor)
        tprint(text, font=font, decoration=decor)
        line()


        def help_func():
        """
    Print help page.

    :return: None
    """
        tprint("art")
        tprint("v" + ART_VERSION)
        print(DESCRIPTION)
        print(CLI_HELP)


        def aprint(artname, number=1):
        """
    Print 1-line art.

    :param artname: artname
    :type artname : str
    :param number: number of repeats
    :type number: int
    :return: None
    """
        try:
        if artname == "UnicodeEncodeError":
        raise UnicodeEncodeError(
        'test', u"", 42, 43, 'test unicode-encode-error')
        print(art(artname=artname, number=number))
        except UnicodeEncodeError:
        print(ART_ENVIRONMENT_WARNING.format(artname))


        def art(artname, number=1):
        """
    Return 1-line art.

    :param artname: artname
    :type artname : str
    :param number: number of repeats
    :type number: int
    :return: ascii art as str
    """
        if isinstance(artname, str) is False:
        raise artError(ART_TYPE_ERROR)
        artname = artname.lower()
        arts = ART_NAMES
        if artname in ["random", "rand", "rnd"]:
        filtered_arts = list(set(arts) - set(RANDOM_FILTERED_ARTS))
        artname = random.choice(filtered_arts)
        elif artname not in art_dic.keys():
        distance_list = list(map(lambda x: distance_calc(artname, x),
        arts))
        min_distance = min(distance_list)
        selected_art = arts[distance_list.index(min_distance)]
        threshold = max(len(artname), len(selected_art)) / 2

