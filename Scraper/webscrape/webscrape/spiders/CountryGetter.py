import scrapy

class CountryGetter(scrapy.Spider):

    name = "country"

    def start_requests(self):

        self.baseRef = "https://www.factmonster.com/world/countries"

        self.count = 0

        urls = [ self.baseRef ]

        custom_settings = {
            'FEED_FORMAT': 'csv',
            'FEED_URI': 'countries.csv'
        }

        for url in urls:
            yield scrapy.Request(url=url, callback=self.parse)

    def parse(self, response):

        for link in response.css('a::attr(href)'):
            linkData = link.get()
            if "/world/countries/" in linkData:
                if "flags" not in linkData:
                    if "ethnicity-and-race-by-countries" not in linkData:
                        if "world-capitals" not in linkData:
                            if "languages-by-countries" not in linkData:
                                yield scrapy.Request(url=self.baseRef + linkData.replace("/world/countries", ""), callback=self.parse_country)

    def parse_country(self, response):

        country = response.css('h1').get().replace("<h1>", "").replace("</h1>", "")
        facts = response.xpath("//div[@class='country-facts-figures']//ul//li//p")

        # Country Facts
        president = ""
        primeMinister = ""
        area = ""
        population = ""

        for fact in facts.extract():

            # Get President if there is one
            if "President:" in fact:
                # Clean up HTML
                president = fact.replace("</p>", "")
                president = president.replace("<p><b>President:</b>", "")
                president = president.replace('<p><b pageno="1">President:</b>', "")
                president = president.replace('<p><strong>President:</strong>', "")

            if "Prime Minister:" in fact:
                #Clean up HTML
                primeMinister = fact.replace('<p><b>Prime Minister:</b>', "")
                primeMinister = primeMinister.replace('<p><strong>Prime Minister:</strong>', "")
                primeMinister = primeMinister.replace('</p>', "")
                primeMinister = primeMinister.replace('<p><b pageno="1">Prime Minister:</b>', "")
                primeMinister = primeMinister.replace('<p><b>Prime Minister: </b>', "")


            # Get total area
            if "Total area:" in fact or "total area:" in fact or "Land area:" in fact:
                # Clean up html
                area = fact.replace('<p><b pageno="1">Total area:</b>', "")
                area = area.replace('<p><b>Land area:</b>', "")
                area = area.replace("</p>", "")
                area = area.replace("<p><b>Total area:</b>", "")
                area = area.replace("<p><strong>Land area:</strong>", "")
                area = area.replace("<p><b>Total area: </b>", "")
                area = area.replace('<p><b pageno="1">Land area:</b>', "")
                area = area.replace('<p><b>Land and total area:</b>', "")
                area = area.replace("<p><strong>Total area:</strong>", "")
                area = area.replace('<sup class="fnr">1</sup>', "")

                if ";" in area:
                    area = area.split(";")[0]

            if "Population" in fact:
                # Clean up html
                if '</b>' in fact:
                    population = fact.split('</b>')[1]
                if '</strong>' in fact:
                    population = fact.split("</strong>")[1]
                population = population.replace("</p>", "")

        yield {
            'country': country,
            'president': president,
            'primeMinister': primeMinister,
            'area': area,
            'population': population
        }
