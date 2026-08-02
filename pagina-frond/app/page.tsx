import { SiteHeader } from '@/components/site-header'
import { Hero } from '@/components/hero'
import { MissionVision } from '@/components/mission-vision'
import { About } from '@/components/about'
import { Development } from '@/components/development'
import { HowItWorks } from '@/components/how-it-works'
import { DownloadCta } from '@/components/download-cta'
import { SiteFooter } from '@/components/site-footer'

export default function Page() {
  return (
    <>
      <SiteHeader />
      <main>
        <Hero />
        <MissionVision />
        <About />
        <Development />
        <HowItWorks />
        <DownloadCta />
      </main>
      <SiteFooter />
    </>
  )
}
